package com.hepta.funcionarios.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hepta.funcionarios.entity.Funcionario;
import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.FuncionarioDAO;
import com.hepta.funcionarios.persistence.SetorDAO;

public class FuncionarioServiceTest {

	/* Método para buscar por codigo o Funcionario */
	@Test
	@Ignore
	public void testFuncionarioRead() throws Exception {

		FuncionarioDAO fDao = new FuncionarioDAO();

		Funcionario funcionario = fDao.find(3);
		System.out.println(funcionario);

	}

	@Test
	@Ignore
	public void testFuncionarioCreate() throws Exception {

		SetorDAO sDao = new SetorDAO();
		Setor setor = sDao.buscarPorCodigo(8);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Jessica Jasmine dos Santos");
		funcionario.setEmail("jessica@hepta.com.br");
		funcionario.setIdade(20);
		funcionario.setSalario(5000.00);
		funcionario.setSetor(setor);

		FuncionarioDAO fDao = new FuncionarioDAO();
		fDao.save(funcionario);

		System.out.println("Salvo " + funcionario + " - " + setor);

	}

	@Test
//	@Ignore
	public void testFuncionarioUpdate() throws Exception {

		FuncionarioDAO fDao = new FuncionarioDAO();
		Funcionario funcionario = fDao.find(4);

		funcionario.setNome("Jessica Jasmine");
		funcionario.setEmail("jean@hepta.com.br");
		funcionario.setIdade(2);
		funcionario.setSalario(5000.00);

		fDao.update(funcionario);
		System.out.println("Editado com sucesso! " + funcionario.getNome());
	}

	@Test
	@Ignore
	public void testFuncionarioDelete() throws Exception {

		FuncionarioDAO fDao = new FuncionarioDAO();
		Funcionario funcionario = fDao.find(6);
		if (funcionario != null) {
			fDao.delete(funcionario);
			System.out.println("Usuário excluído com sucesso! ");
		}

	}

	@Test
	@Ignore
	public void listar() throws Exception {
		FuncionarioDAO fDao = new FuncionarioDAO();

		List<Funcionario> funcionario = fDao.getAll();
		System.out.println(funcionario);

	}

}
