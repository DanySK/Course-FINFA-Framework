package it.unibo.frameworkfv;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import static it.unibo.frameworkfv.Constants.FONT_SIZE;

/**
 * @author eoliva
 * @author Danilo Pianini
 * 
 *         Created on 29 ottobre 2005, 15.29. Modified on Jan 11th, 2013
 */

public class JavaFS extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = -2355893182297255013L;
	private static final String CNAME = "FrameworkFS";

	/** Creates new form NewJFrame */
	public JavaFS() {
		initComponents();
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jPanel3 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea3 = new javax.swing.JTextArea();

		getContentPane().setLayout(new java.awt.GridLayout(2, 1));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jPanel1.setLayout(new java.awt.GridLayout(1, 1));

		jScrollPane1.setBorder(new javax.swing.border.TitledBorder("Dichiarazione di funzioni"));
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jPanel1.add(jScrollPane1);

		jScrollPane2.setBorder(new javax.swing.border.TitledBorder("Blocco da eseguire"));
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		// jPanel1.add(jScrollPane2);

		getContentPane().add(jPanel1);

		jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.Y_AXIS));
		// jPanel3.setLayout(new java.awt.BorderLayout());
		jPanel3.add(jScrollPane2);

		jButton1.setText("Esecuzione");
		/*
		 * jButton1.addActionListener(new java.awt.event.ActionListener() {
		 * public void actionPerformed(java.awt.event.ActionEvent evt) {
		 * jButton1ActionPerformed(evt); } });
		 */
		jButton1.addActionListener(this);

		jPanel2.add(jButton1);

		// jPanel3.add(jPanel2, java.awt.BorderLayout.NORTH);
		jPanel3.add(jPanel2);

		jScrollPane3.setBorder(null);
		jScrollPane3.setViewportBorder(new javax.swing.border.TitledBorder("Risultato della valutazione"));
		jTextArea3.setRows(8);
		jScrollPane3.setViewportView(jTextArea3);

		// jPanel3.add(jScrollPane3, java.awt.BorderLayout.CENTER);
		jPanel3.add(jScrollPane3);

		jTextArea1.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		jTextArea1.setTabSize(2);
		jTextArea2.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		jTextArea2.setTabSize(2);
		jTextArea3.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
		jTextArea3.setTabSize(2);
		jTextArea3.setEditable(false);

		getContentPane().add(jPanel3);

		pack();
	}

	// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextArea jTextArea3;

	private void showMsg(String msg) {
		jTextArea3.setText(msg);
		jTextArea3.setCaretPosition(0);
	}

	private void doRun() throws IOException {
		final StringBuilder sb = new StringBuilder();
		sb.append("public class " + CNAME + " {\n");
		sb.append("\tpublic String s=\" \";\n");
		if (jTextArea1.getText().compareTo("") != 0) {
			sb.append("\t");
			sb.append(jTextArea1.getText());
		}
		sb.append("\n\tpublic String print(String app){" + "s=s+app; return s;}");
		sb.append("\n\tpublic String println(String app){" + "s=s+app+\" \\n \"; return s; }");
		sb.append("\n\tpublic String getResult(){\n");
		sb.append("\t\t" + jTextArea2.getText() + "\n");
		sb.append("\t\treturn s;\n");
		sb.append("\t}\n");
		sb.append("}");

		showMsg(DynaComp.interpret(CNAME, sb.toString()));
	}

	/**
	 * Listens to actions from the run button.
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (jButton1 == source) {
			// Disable the run button while we're running

			jButton1.setEnabled(false);
			try {
				doRun();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			jButton1.setEnabled(true);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		JFrame f = new JavaFS();
		f.setTitle("Framework F-S 2013");
		f.setBounds(100, 30, 800, 700);
		f.setVisible(true);

		f.validate();
	}

}
