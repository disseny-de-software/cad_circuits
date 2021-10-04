package new_classes;

import simple.*;

public class Client {
	private static final boolean[][] table = {{false,false},{false,true},{true,false},{true,true}};

	private static void testXor() {
		System.out.println("\nTest simulation of an Xor as object of a class Xor");
		Xor xor1 = new Xor();
		System.out.println("name : " + xor1.getName());
		for(int i=0; i<4; i++) {
			xor1.setInput(0, table[i][0]);
			xor1.setInput(1, table[i][1]);
			xor1.process();
			System.out.println("xor(" + table[i][0] +"," + table[i][1] + ") = " + xor1.isStateOutput());
		}
	}

	private static void testAdder1Bit() {
		System.out.println("\nTest simulation of an adder of 1 bit with 3 inputs");
		Adder1Bit s1 = new Adder1Bit();
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<2; k++) {
					s1.getPinInput(0).setState((i==1));
					s1.getPinInput(1).setState((j==1));
					s1.getPinInput(2).setState((k==1));
					s1.process();
					boolean suma = s1.isStateOutput(0);
					boolean carry = s1.isStateOutput(1);
					System.out.println(i + "+" + j + "+" + k + " =  " + (carry?1:0) + (suma?1:0) );
				}
			}
		}
	}

	private static void testAdder2Bits() {
		System.out.println("\nTest of an adder of n=2 bits");
		AdderNBits s2 = new AdderNBits(2);
		for(int i=0; i<4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(i + " + " + j + " = " + s2.sumaDecimal(i, j));
			}
		}
	}

	public static void main(String[] args) {
		testXor();
		testAdder1Bit();
		testAdder2Bits();
	}
}
