package com.ohdear;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame("Generate Primes");
        JButton b=new JButton("Start process");
        f.setBackground(Color.BLUE);
        final JTextField c= new JTextField("");
        final JTextField d =new JTextField("");
        final JTextField ee =new JTextField("");
        final JLabel l1=new JLabel("N");
        final JLabel l2=new JLabel("Buffer Size");
        final JLabel l3=new JLabel("Output File");
        f.setLayout(null);
        b.setBounds(250,350,140,50);
        c.setBounds(10,50,170,25);
        d.setBounds(10,150,170,25);
        ee.setBounds(10,250,170,25);
        l1.setBounds(250 ,15,150,100);
        l2.setBounds(230 ,115,150,100);
        l3.setBounds(230 ,215,150,100);
        c.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        d.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        ee.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b.setFont(new Font("Times New Roman", Font.BOLD, 18));
        c.setBackground(Color.white);
        d.setBackground(Color.white);
        ee.setBackground(Color.WHITE);
        b.setBackground(Color.CYAN);
        l1.setForeground(Color.red);
        l2.setForeground(Color.red);
        l3.setForeground(Color.red);
        f.add(b);
        f.add(c);
        f.add(d);
        f.add(ee);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.getContentPane().setBackground(Color.black);
        f.setSize(700,700);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n= Integer.parseInt(c.getText());
                int bufferSize= Integer.parseInt(d.getText());
                String fileName= ee.getText();
                try {
                    PrimeBuffer primeBuffer = new PrimeBuffer(bufferSize,n,fileName);
                    Producer pp=new Producer(primeBuffer,n);
                    long startTime = System.currentTimeMillis();
                    Consumer co=new Consumer(primeBuffer,startTime);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
