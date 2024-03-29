/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfas;

import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Animacion
{
    public void jLabelYUp(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
        if (jLabel.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jLabel.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jLabel.setLocation(jLabel.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jLabel.setLocation(jLabel.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jLabelYDown(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
        if (jLabel.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jLabel.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jLabel.setLocation(jLabel.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jLabel.setLocation(jLabel.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jLabelXLeft(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
        if (jLabel.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jLabel.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jLabel.setLocation(i, jLabel.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jLabel.setLocation(stop, jLabel.getY());
                }
            }.start();
        }
    }
    
    public void jLabelXRight(final int start, final int stop, final int delay, final int increment, final JLabel jLabel) {
        if (jLabel.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jLabel.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jLabel.setLocation(i, jLabel.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jLabel.setLocation(stop, jLabel.getY());
                }
            }.start();
        }
    }
    
    public void jTextFieldYUp(final int start, final int stop, final int delay, final int increment, final JTextField jTextField) {
        if (jTextField.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jTextField.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jTextField.setLocation(jTextField.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jTextField.setLocation(jTextField.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jTextFieldYDown(final int start, final int stop, final int delay, final int increment, final JTextField jTextField) {
        if (jTextField.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jTextField.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jTextField.setLocation(jTextField.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jTextField.setLocation(jTextField.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jTextFieldXLeft(final int start, final int stop, final int delay, final int increment, final JTextField jTextField) {
        if (jTextField.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jTextField.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jTextField.setLocation(i, jTextField.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jTextField.setLocation(stop, jTextField.getY());
                }
            }.start();
        }
    }
    
    public void jTextFieldXRight(final int start, final int stop, final int delay, final int increment, final JTextField jTextField) {
        if (jTextField.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jTextField.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jTextField.setLocation(i, jTextField.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jTextField.setLocation(stop, jTextField.getY());
                }
            }.start();
        }
    }
    
    public void jButtonYUp(final int start, final int stop, final int delay, final int increment, final JButton jButton) {
        if (jButton.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jButton.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jButton.setLocation(jButton.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jButton.setLocation(jButton.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jButtonYDown(final int start, final int stop, final int delay, final int increment, final JButton jButton) {
        if (jButton.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jButton.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jButton.setLocation(jButton.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jButton.setLocation(jButton.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jButtonXLeft(final int start, final int stop, final int delay, final int increment, final JButton jButton) {
        if (jButton.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jButton.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jButton.setLocation(i, jButton.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jButton.setLocation(stop, jButton.getY());
                }
            }.start();
        }
    }
    
    public void jButtonXRight(final int start, final int stop, final int delay, final int increment, final JButton jButton) {
        if (jButton.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jButton.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jButton.setLocation(i, jButton.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jButton.setLocation(stop, jButton.getY());
                }
            }.start();
        }
    }
    
    public void jPasswordFieldYUp(final int start, final int stop, final int delay, final int increment, final JPasswordField jPasswordField) {
        if (jPasswordField.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jPasswordField.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jPasswordField.setLocation(jPasswordField.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jPasswordField.setLocation(jPasswordField.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jPasswordFieldYDown(final int start, final int stop, final int delay, final int increment, final JPasswordField jPasswordField) {
        if (jPasswordField.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jPasswordField.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jPasswordField.setLocation(jPasswordField.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jPasswordField.setLocation(jPasswordField.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jPasswordFieldXLeft(final int start, final int stop, final int delay, final int increment, final JPasswordField jPasswordField) {
        if (jPasswordField.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jPasswordField.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jPasswordField.setLocation(i, jPasswordField.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jPasswordField.setLocation(stop, jPasswordField.getY());
                }
            }.start();
        }
    }
    
    public void jPasswordFieldXRight(final int start, final int stop, final int delay, final int increment, final JPasswordField jPasswordField) {
        if (jPasswordField.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jPasswordField.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jPasswordField.setLocation(i, jPasswordField.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jPasswordField.setLocation(stop, jPasswordField.getY());
                }
            }.start();
        }
    }
    
    public void jTextAreaYUp(final int start, final int stop, final int delay, final int increment, final JScrollPane jScrollPane) 
    {
        if (jScrollPane.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(jScrollPane.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(jScrollPane.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jTextAreaYDown(final int start, final int stop, final int delay, final int increment, final JScrollPane jScrollPane) 
    {
        if (jScrollPane.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(jScrollPane.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(jScrollPane.getX(), stop);
                }
            }.start();
        }
    }
    
    public void jTextAreaXLeft(final int start, final int stop, final int delay, final int increment, final JScrollPane jScrollPane) 
    {
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                }
            }.start();
        }
    }
    
    public void jTextAreaXRight(final int start, final int stop, final int delay, final int increment, final JScrollPane jScrollPane) 
    {
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                }
            }.start();
        }
    }
    
    
    /*-------------- movimiento de panel hacia arriba ----------------*/
    
    public void jPanelYUp(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane) 
    {
        if (jScrollPane.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getY() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(jScrollPane.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(jScrollPane.getX(), stop);
                }
            }.start();
        }
    }
    
    /*----------Panel hacia abajo-----------*/
    
    public void jPanelYDown(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane) 
    {
        if (jScrollPane.getY() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getY() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(jScrollPane.getX(), i);
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(jScrollPane.getX(), stop);
                }
            }.start();
        }
    }
    
    /*-------- Panel hacia la izquierda ------------*/
    public void jPanelXLeft(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane) 
    {
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                }
            }.start();
        }
    }
    
    /*Panel hacia derecha*/
    
    public void jPanelXRight(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane) 
    {
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    while (jScrollPane.getX() <= start) {
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                }
            }.start();
        }
    }
    
    /*Metodos Nuevos*/
    /*-------- Panel hacia la izquierda ------------*/
    public void NavegadorXLeft(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane, final JButton btn, final JButton btn2) 
    {
        
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    if(btn != null && btn2 != null){
                    btn.setEnabled(false);
                    btn2.setEnabled(false);}
                    
                    while (jScrollPane.getX() > stop) {
                        for (int i = start; i >= stop; i -= increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                    
                    if(btn != null && btn2 != null){
                    btn.setEnabled(true);
                    btn2.setEnabled(true);}
                    //termino = true;
                }
            }.start();
        }
        //return termino;
    }
    
    /*Panel hacia derecha*/
    
    public void NavegadorXRight(final int start, final int stop, final int delay, final int increment, final JPanel jScrollPane, final JButton btn, final JButton btn2) 
    {   
        //termino = false;
        if (jScrollPane.getX() == start) {
            new Thread() {
                @Override
                public void run() {
                    if(btn != null && btn2 != null){
                    btn.setEnabled(false);
                    btn2.setEnabled(false);}
                    while (jScrollPane.getX() <= start) {
                        
                        for (int i = start; i <= stop; i += increment) {
                            try {
                                Thread.sleep(delay);
                                jScrollPane.setLocation(i, jScrollPane.getY());
                            }
                            catch (InterruptedException e) {
                                System.out.println("Error Thread Interrupted: " + e);
                            }
                        }
                    }
                    jScrollPane.setLocation(stop, jScrollPane.getY());
                    if(btn != null && btn2 != null){
                    btn.setEnabled(true);
                    btn2.setEnabled(true);}
                }
            }.start();
            
        }
        //return termino;
    }
}
