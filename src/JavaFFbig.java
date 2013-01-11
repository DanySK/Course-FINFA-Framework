import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;


/*
 * Created on 29-ott-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author eoliva
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/*
 * NewJFrame.java
 *
 * Created on 29 ottobre 2005, 15.29
 */

public class JavaFFbig extends javax.swing.JFrame implements ActionListener{
    
    /** Creates new form NewJFrame */
    public JavaFFbig() {
        initComponents();
    }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
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
        jTextArea1.setRows(4);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);

        jScrollPane2.setBorder(new javax.swing.border.TitledBorder("Espressione da valutare"));
        jTextArea2.setRows(3);
        jScrollPane2.setViewportView(jTextArea2);
        
        //jPanel3.setLayout(new java.awt.GridLayout(3, 1));
        //jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.setLayout(new BoxLayout(jPanel3,BoxLayout.Y_AXIS));
        
        //jPanel3.add(jScrollPane2, java.awt.BorderLayout.NORTH);
        jPanel3.add(jScrollPane2);
        
        getContentPane().add(jPanel1);

        

        jButton1.setText("Valutazione");
        /*jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });*/
        jButton1.addActionListener(this);
        //jPanel2.setLayout(new java.awt.GridLayout(1, 3));
        jPanel2.add(jButton1);
        jPanel2.validate();

        //jPanel3.add(jPanel2, java.awt.BorderLayout.CENTER); 
        jPanel3.add(jPanel2);
        //jPanel3.add(jButton1);
        
        jScrollPane3.setBorder(null);
        jScrollPane3.setViewportBorder(new javax.swing.border.TitledBorder("Risultato della valutazione"));
        jTextArea3.setRows(8);
        jScrollPane3.setViewportView(jTextArea3);

        //jPanel3.add(jScrollPane3, java.awt.BorderLayout.SOUTH);
        jPanel3.add(jScrollPane3);
        
        jTextArea1.setFont(new Font("Monospaced", Font.BOLD, 24));
		jTextArea1.setTabSize(2);
		jTextArea2.setFont(new Font("Monospaced", Font.BOLD, 24));
		jTextArea2.setTabSize(2);
		jTextArea3.setFont(new Font("Monospaced", Font.BOLD, 24));
		jTextArea3.setTabSize(2);
        
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
    // End of variables declaration                   
    
    
    /** The compiler object. */
    private com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();
   
 
    private void showMsg(String msg) {
    	jTextArea3.setText(msg);
        //System.err.println(msg);
    }
    private void appendMsg(String msg) {
    	jTextArea3.append(msg);
        //System.err.println(msg);
    }
    
    
private void doRun() throws IOException {
    // Create a temp. file

    File file = File.createTempFile("jav", ".java",
            new File(System.getProperty("user.dir")));

    // Set the file to be deleted on exit

    file.deleteOnExit();

    // Get the file name and extract a class name from it

    String filename = file.getName();
    String classname = filename.substring(0, filename.length()-5);

    // Output the source

    PrintWriter out = new PrintWriter(new FileOutputStream(file));
    out.println("/**");
    out.println(" * Source created on " + new Date());
    out.println(" */");
    out.println("public class " + classname + " {");
    
    out.print("public "); out.println(jTextArea1.getText());

    out.println("public String getResult(){");
    out.println("  return \"\" ");
    if (jTextArea2.getText().compareTo("")!=0) {
    	out.print("+(");out.print(jTextArea2.getText());
    	out.print(")");
    } 
    	
    out.println(";");
    out.println("}");
    
    //out.println("    public static void main(String[] args) throws Exception {");

    // Your short code segment

    // out.print("      "); out.print(text.getText());
    
     //out.print("System.out.println("); out.print(text.getText());
     //out.println(");");  
    
     
     //out.println("    }");
     out.println("}");

    // Flush and close the stream

    out.flush();
    out.close();

    // Compile

    String[] args = new String[] {
        "-d", System.getProperty("user.dir"),
        filename
    };
   
    //Writer out1 = new BufferedWriter(new OutputStreamWriter(System.out));
    
    PrintWriter arg1= new PrintWriter("err.txt");
   
    int status;
    status = javac.compile(args,arg1);
   
    // Run

    switch (status) {
        case 0:  // OK
            // Make the class file temporary as well
            //showMsg("Compile status OK");
      
            new File(file.getParent(), classname + ".class").deleteOnExit();

            try {
                // Try to access the class and run its main method

                Class clazz = Class.forName(classname);
                Object myClass = clazz.newInstance();
                
                //System.out.println(clazz.getName());
                Method getResult = clazz.getMethod("getResult",clazz.getClasses() );
                 
                //System.out.println(getResult.getName()); 
                jTextArea3.setText((String)getResult.invoke (myClass, null));
                
            } catch (InvocationTargetException ex) {
                // Exception in the main method that we just tried to run

                showMsg("Exception in main: " + ex.getTargetException());
                //ex.getTargetException().printStackTrace();
            } catch (Exception ex) {
                showMsg(ex.toString());
            }
            break;
        case 1: showMsg("Compile status: ERROR"+"\n");  
                BufferedReader in
		           = new BufferedReader(new FileReader("err.txt"));
                //showMsg(System.getProperty("user.dir"));
                while (in.ready()) 
                	appendMsg(in.readLine()+"\n" );
        	    break;
        	    
        case 2: showMsg("Compile status: CMDERR"); break;
        case 3: showMsg("Compile status: SYSERR"); break;
        case 4: showMsg("Compile status: ABNORMAL"); break;
        default:
            showMsg("Compile status: Unknown exit status");
    }
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
 * @param args the command line arguments
 */
public static void main(String args[]) {
	
	JFrame f= new JavaFFbig();
	f.setTitle("Framework F-F");
	f.setBounds(100,30,1024,768);
	f.setVisible(true);
	
	f.validate();
	//f.pack();
	 
/*        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new JavaFF().setVisible(true);
        }
    });*/
}

}
