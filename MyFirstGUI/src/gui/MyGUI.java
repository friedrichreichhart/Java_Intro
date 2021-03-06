package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import data.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGUI {

	protected Shell shell;
	private Text vorname;
	private Text nachname;
	private Text adresse;
	private Text postleitzahl;
	private Text ort;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyGUI window = new MyGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackgroundImage(null);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		shell.setSize(391, 412);
		shell.setText("SWT Application");

		vorname = new Text(shell, SWT.BORDER);
		vorname.setBounds(104, 33, 196, 24);

		Label lblVorname = new Label(shell, SWT.NONE);
		lblVorname.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblVorname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblVorname.setBounds(45, 36, 53, 21);
		lblVorname.setText("Vorname");

		nachname = new Text(shell, SWT.BORDER);
		nachname.setBounds(104, 63, 196, 24);

		adresse = new Text(shell, SWT.BORDER);
		adresse.setBounds(104, 93, 196, 24);

		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Wide Latin", 9, SWT.NORMAL));
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {

					Person p = new Person();
					p.setVorname(getVorname().getText());
					p.setNachname(getNachname().getText());
					p.setAdresse(getAdresse().getText());
					p.setPostleitzahl(Integer.parseInt(getPostleitzahl().getText()));
					p.setOrt(getOrt().getText());

					// System.out.println(p);

					Person.getPersonenliste().add(p);

					for (int i = 0; i < Person.getPersonenliste().size(); i++) {

						System.out.println(Person.getPersonenliste().get(i));

					}

					

				} catch (NumberFormatException nfe) {
					MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					mb.setMessage("Fehler bei der Postleitzahl");
					mb.setText("Fehler");
					mb.open();

				}
			}
		});
		button.setText("Hallo");
		button.setBounds(144, 193, 75, 25);

		Label lblNachname = new Label(shell, SWT.NONE);
		lblNachname.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblNachname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNachname.setBounds(34, 66, 64, 15);
		lblNachname.setText("Nachname");

		Label lblAdresse = new Label(shell, SWT.NONE);
		lblAdresse.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblAdresse.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAdresse.setBounds(43, 96, 55, 15);
		lblAdresse.setText("Adresse");

		postleitzahl = new Text(shell, SWT.BORDER);
		postleitzahl.setBackgroundImage(SWTResourceManager.getImage(
				"\\\\ams-gym-pfs\\schuelerprofiledata\\clemensthalhammer\\Desktop\\SCHUL_ALBERTUS_LOGO-Kopie.jpg"));
		postleitzahl.setBounds(104, 123, 196, 21);

		Label lblPostleitzahl = new Label(shell, SWT.NONE);
		lblPostleitzahl.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblPostleitzahl.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPostleitzahl.setBounds(25, 129, 64, 15);
		lblPostleitzahl.setText("Postleitzahl");

		Label lblOrt = new Label(shell, SWT.NONE);
		lblOrt.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblOrt.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblOrt.setBounds(43, 153, 55, 15);
		lblOrt.setText("Ort");

		ort = new Text(shell, SWT.BORDER);
		ort.setBounds(104, 150, 196, 21);

		Button btnjson = new Button(shell, SWT.NONE);
		btnjson.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					Person.write2json();
					clearMask();
					
					MessageBox mb = new MessageBox(shell,SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("Erfolg");
					mb.setText(Person.getPersonenliste().size()+" Eintr�ge erfolgreich geschrieben");
					mb.open();
			}
		});
		btnjson.setBounds(144, 243, 75, 25);
		btnjson.setText("2Json");

	}

	protected void clearMask() {
		// TODO Auto-generated method stub
		vorname.setText("");
		nachname.setText("");
		adresse.setText("");
		postleitzahl.setText("");
		ort.setText("");
	}

	protected Text getVorname() {
		return vorname;
	}

	protected Text getNachname() {
		return nachname;
	}

	protected Text getAdresse() {
		return adresse;
	}

	protected Text getPostleitzahl() {
		return postleitzahl;
	}

	protected Text getOrt() {
		return ort;
	}
}
