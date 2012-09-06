package br.com.renan.model;

import br.com.renan.aspect.InjetaRepositorio;
import br.com.renan.repository.AlunoRepository;

public class Aluno {

	@InjetaRepositorio
	public AlunoRepository	alunoRepository;

	public int				idade;

	public Aluno(int idade) {
		System.out.println("constroi");
	}

	public Aluno() {
		System.out.println("constroi");
	}

	public void dizOla() {
		System.out.println("ola");
	}

	public double calculaMedia() {
		return alunoRepository.calculaMedia();
	}

	public AlunoRepository getAlunoRepository() {
		return alunoRepository;
	}

	public void setAlunoRepository(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

}