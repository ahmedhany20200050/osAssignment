package com.ohdear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Consumer implements Runnable{
    private final PrimeBuffer primeBuffer;
    private final long startTime;
    public long executionTime=0;
    JFrame f1=new JFrame("Generate Primes");
    final JTextField g =new JTextField("");
    final JTextField h =new JTextField("");
    final JTextField i =new JTextField("");
    final JLabel l1=new JLabel("The largest prime number");
    final JLabel l2=new JLabel("# of elements(prime number) generated");
    final JLabel l3=new JLabel("Time elapsed since the start of processing in (ms)");
    Consumer(PrimeBuffer primeBuffer,long startTime) {
        this.primeBuffer = primeBuffer;
        this.startTime = startTime;
        Thread t = new Thread(this, "Consumer");
        t.start();
        g.setBounds(10,50,170,25);
        h.setBounds(10,150,170,25);
        i.setBounds(10,250,170,25);
        l1.setBounds(250 ,15,400,100);
        l2.setBounds(230 ,115,400,100);
        l3.setBounds(230 ,215,450,100);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        h.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        i.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.setBackground(Color.white);
        h.setBackground(Color.white);
        i.setBackground(Color.WHITE);
        l1.setForeground(Color.red);
        l2.setForeground(Color.red);
        l3.setForeground(Color.red);
        f1.add(g);
        f1.add(h);
        f1.add(i);
        f1.add(l1);
        f1.add(l2);
        f1.add(l3);
        f1.getContentPane().setBackground(Color.black);
        f1.setSize(700,700);
        f1.setLayout(null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    @Override
    public void run() {
        int ii=0;
        g.setText("");
        h.setText("");
        i.setText("");
        while (true){
            try {
                primeBuffer.consume();
            } catch (InterruptedException | IOException e) {
                return;
            }

            long endTime = System.currentTimeMillis();
            executionTime = endTime-startTime;
            if(primeBuffer.getIsOperationFinished() && primeBuffer.getPrimes().isEmpty()){
                g.setText(String.valueOf(primeBuffer.getMaxPrimeValue()));
                h.setText(String.valueOf(primeBuffer.getNumberOfPrimeNumbers()));
                i.setText(String.valueOf(executionTime));
                break;
            }
            g.setText(String.valueOf(primeBuffer.getMaxPrimeValue()));
            h.setText(String.valueOf(primeBuffer.getNumberOfPrimeNumbers()));
            i.setText(String.valueOf(executionTime));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
