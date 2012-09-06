package br.com.renan.aspect;

import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.Matcher;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.renan.repository.AlunoRepository;

@Aspect
public class AlunoAspecto {

	private AlunoRepository	alunos;

	@After("execution(!private br.com.renan.model.*.new(..))")
	public void depoisDeConstruir(JoinPoint jp) {
		System.out.println("inicio do aspecto");

		Object target = jp.getTarget();

		List<Field> l = new Mirror().on(target.getClass()).reflectAll().fields().matching(new RepMatcher());
		for (Field field : l) {
			System.out.println("Field: " + field.getName());
			System.out.println(field.getType());

			new Mirror().on(target).set().field(field).withValue(alunos);
		}

		System.out.println("depois de construir");
	}

	private class RepMatcher implements Matcher<Field> {
		public boolean accepts(Field element) {
			return element.isAnnotationPresent(InjetaRepositorio.class);
		}
	}

	public AlunoRepository getAlunos() {
		return alunos;
	}

	@Autowired
	public void setAlunos(AlunoRepository alunos) {
		this.alunos = alunos;
	}

	@After("execution(* br.com.renan.model.Aluno.dizOla(..))")
	public void depoisDeDizerOla(JoinPoint jp) {
		System.out.println(alunos.calculaMedia());
		System.out.println("foi embora");
	}

}