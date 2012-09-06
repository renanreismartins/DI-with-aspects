package br.com.renan.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.renan.model.Aluno;

public class Teste {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("====");
		System.out.println(new Aluno().calculaMedia());
	}
}