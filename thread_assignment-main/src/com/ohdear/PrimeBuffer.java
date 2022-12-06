package com.ohdear;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class PrimeBuffer {
    int bufferSize;
    private final List<Long> primes;
    private long numberOfPrimeNumbers=0;
    private final int n;
    boolean isOperationFinished=false;
    File file;
    FileWriter fileWriter;
    PrintWriter printWriter;
    private long maxPrimeValue=0;

    PrimeBuffer(int bufferSize,int n,String fileName) throws IOException {
        this.bufferSize=bufferSize;
        file = new File(fileName);
        fileWriter = new FileWriter(file);
        printWriter = new PrintWriter(fileWriter);
        this.primes=new ArrayList<>();
        this.n=n;
    }

    public List<Long> getPrimes() {
        return primes;
    }

    public long getMaxPrimeValue() {
        return maxPrimeValue;
    }

    public long getNumberOfPrimeNumbers() {
        return numberOfPrimeNumbers;
    }

    public boolean getIsOperationFinished() {
        return isOperationFinished;
    }

    public synchronized void produce(long val) throws InterruptedException {
        while(primes.size()==bufferSize){
            wait();
        }
        if(isPrime(val)){
            primes.add(val);
            if(n % 2 == 0){
                if(val == n - 1 && n > 2){
                    isOperationFinished=true;
                }else {
                   if(val == n){
                       isOperationFinished=true;
                   }
                }
            }else{
                if(val == n){
                    isOperationFinished=true;
                }
            }
            numberOfPrimeNumbers++;
            maxPrimeValue=val;
            notify();
        }
        else{
            if(n % 2 == 0){
                if(val == n - 1){
                    isOperationFinished=true;
                }
            }else{
                if(val == n){
                    isOperationFinished=true;
                }
            }
            notify();
        }
    }
    public synchronized void consume() throws IOException, InterruptedException {
        while(primes.isEmpty()){
            if(isOperationFinished)
                break;
            wait();
        }
        while(!primes.isEmpty()){
            long primeValue=primes.get(0);
            printWriter.print(primeValue);
            printWriter.print(" ");
            primes.remove(0);
        }
        if(isOperationFinished){
            printWriter.close();
            return;
        }
        notify();
    }
    public boolean isPrime(long val){
        if(val==2 || val==1)return true;
        if(val%2==0)return false;
        for(long i=3;i*i<=val;i+=2){
            if(val%i==0){
                return false;
            }
        }
        return true;
    }
}
