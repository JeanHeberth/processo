package com.hepta.funcionarios.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.SetorDAO;

public class SetorServiceTest {

	/* M�todo para salvar o setor */
	@Test
//	@Ignore
	public void salvar() throws Exception {

		Setor setor = new Setor();

		setor.setNome("Banco de dados");
		SetorDAO sDao = new SetorDAO();
		sDao.salvar(setor);
		System.out.println("Usu�rio salvo " + setor);
	}

	/* M�todo para buscar por codigo o setor */
	@Test
	@Ignore
	public void buscarPorCodigo() throws Exception {

		SetorDAO sDao = new SetorDAO();
		Setor setor = sDao.buscarPorCodigo(1);
		System.out.println(setor);

	}

	/* M�todo para excluir o setor */
	@Test
	@Ignore
	public void delete() throws Exception {

		SetorDAO sDao = new SetorDAO();
		Setor setor = sDao.buscarPorCodigo(4);

		if (setor != null) {
			sDao.delete(setor);
			System.out.println("Exclu�do com sucesso!");
		}

	}

	/* M�todo para listar o setor */
	@Test
	@Ignore
	public void listar() throws Exception {

		SetorDAO sDao = new SetorDAO();

		List<Setor> setores = sDao.listar();
		System.out.println(setores);

	}

	/* M�todo para editar o setor */
	@Test
	@Ignore
	public void editar() throws Exception {

		Setor setor = new Setor();
		setor.setId(7);
		setor.setNome("Seguran�a da Informa��o");

		SetorDAO sDao = new SetorDAO();
		sDao.editar(setor);
		System.out.println("Editado com sucesso! " + setor.getNome());

	}
}