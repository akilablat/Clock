import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;

public class Clock1 extends JFrame implements ActionListener {

    JFrame frame;
    JButton swi;
    JPanel p1,p2;
    int hour,min,sec,aa;
    Date date;
    String time,ahour,bmin,csec;

    public Clock1() {
        date = new Date();
        time = date.toString();
        hour = Integer.parseInt(time.substring(11,13));
        min = Integer.parseInt(time.substring(14,16));
        sec = Integer.parseInt(time.substring(17,19));
        ahour = "";  
        bmin = "";  
        csec = ""; 
        aa = 0;

        Thread ClockEngine=new Thread(){

        int newsec,newmin;
    
        public void run()
          {
          while(true)
            {
            newsec=(sec+1)%60;
            newmin=(min+(sec+1)/60)%60;
            hour=(hour+(min+(sec+1)/60)/60)%12;
            sec=newsec;
            min=newmin;
        
            try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {}
            p1.repaint();  
            p2.repaint();  
            }
        }
        };
        ClockEngine.setPriority(ClockEngine.getPriority()+3);
        ClockEngine.start();

        swi = new JButton("<html>Switch<br>Style</html");
        swi.setBounds(490, 0, 120, 500);
        swi.setFont(new Font("Arial", Font.BOLD, 20));
        swi.setBackground(Color.black);
        swi.setFocusable(false);
        swi.addActionListener(this);
        swi.setForeground(Color.white);

        p1 = new JPanel(){
            @Override
            public void paint(Graphics g){
                super.paint(g);
                g.setColor(Color.WHITE);
                g.fillOval(5, 5,480,480);
                g.setColor(Color.BLACK);
                g.fillOval(10, 10,470,470);
                g.setColor(Color.WHITE);
                g.fillOval(237,237,15,15);
                g.setFont(g.getFont().deriveFont(Font.BOLD,32));
        
                for(int i=1;i<=12;i++)
                    g.drawString(Integer.toString(i),240-(i/12)*11+(int)(210*Math.sin(i*Math.PI/6)),253-(int)(210*Math.cos(i*Math.PI/6)));
        
                double minsecdeg=(double)Math.PI/30;
                double hrdeg=(double)Math.PI/6;
                int tx,ty;
                int xpoints[]=new int[3];
                int ypoints[]=new int[3];
         
                tx=245+(int)(210*Math.sin(sec*minsecdeg));
                ty=245-(int)(210*Math.cos(sec*minsecdeg));
                g.drawLine(245,245,tx,ty);
        
                tx=245+(int)(190*Math.sin(min*minsecdeg));
                ty=245-(int)(190*Math.cos(min*minsecdeg));
                xpoints[0]=245;
                xpoints[1]=tx+2;
                xpoints[2]=tx-2;
                ypoints[0]=245;
                ypoints[1]=ty+2;
                ypoints[2]=ty-2;
                g.fillPolygon(xpoints, ypoints,3);
        
                tx=245+(int)(160*Math.sin(hour*hrdeg+min*Math.PI/360));
                ty=245-(int)(160*Math.cos(hour*hrdeg+min*Math.PI/360));
                xpoints[1]=tx+4;
                xpoints[2]=tx-4;
                ypoints[1]=ty-4;
                ypoints[2]=ty+4;
                g.fillPolygon(xpoints, ypoints, 3);
                }
            };
        p1.setBounds(0,0,510,530);
        p1.setBackground(Color.black);
        p1.setVisible(true);

        p2 = new JPanel(){
            @Override
            public void paint(Graphics v){
                super.paint(v);
                if (hour < 10) {  
                    ahour = "0";  
                }  
                if (hour >= 10) {  
                    ahour = "";  
                }  
                if (min < 10) {  
                    bmin = "0";  
                }  
                if (min >= 10) {  
                    bmin = "";  
                }  
                if (sec < 10) {  
                    csec = "0";  
                }  
                if (sec >= 10) {  
                    csec = "";  
                }  
                v.setColor(Color.white);
                v.setFont(v.getFont().deriveFont(Font.BOLD,40));
                v.drawString(ahour+Integer.toString(hour)+":"+bmin+Integer.toString(min)+":"+csec+Integer.toString(sec),200,260);
            }
        };
        p2.setBackground(Color.black);
        p2.setBounds(0,0,510,530);
        
        p2.setVisible(false);

       
        frame = new JFrame("Clock");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(620,530);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBackground(Color.black);
        frame.add(swi);
        frame.add(p1);
        frame.add(p2);
    }

        


     
public static void main(String args[]) {
                Clock1 clock1 = new Clock1();
    }
    @Override
public void actionPerformed(ActionEvent c){ 
    if (c.getSource() == swi){ 
        if(aa == 0){
            p1.setVisible(false);
            p2.setVisible(true);
            aa = aa+1;
        }
        else if (aa == 1){
            p1.setVisible(true);
            p2.setVisible(false);
            aa = aa-1;
        }
    }
           
          
        
    }

}