package com.hepta.funcionarios.Bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.SetorDAO;
import com.hepta.funcionarios.util.JSFUtil;

@ManagedBean(name = "MBSetor")
@ViewScoped
public class SetorBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Setor setor;
	private List<Setor> setores;

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	/**
	 *
	 * @throws Exception
	 */
	@PostConstruct
	public void listagem() {

		try {
			SetorDAO sDao = new SetorDAO();
			setores = sDao.listar();
		} catch (Exception ex) {
			Logger.getLogger(SetorBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void novo() {
		setor = new Setor();
	}

	public void salvar() throws Exception {

		try {
			if (!setor.getNome().equals("")) {
				SetorDAO sDao = new SetorDAO();
				sDao.merge(setor);

				novo();
				setores = sDao.listar();

				JSFUtil.adicionarMessagemDeSucesso("Setor cadastrado com sucesso");

			} else {

				JSFUtil.adicionarMensagemDeErro("Setor não pode ser vazio.");

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemDeErro(e.getMessage());

		}

	}

	public void excluir(ActionEvent evento) throws Exception {
		try {
			setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionado");

			SetorDAO sDao = new SetorDAO();
			sDao.delete(setor);

			setores = sDao.listar();

			JSFUtil.adicionarMessagemDeSucesso("Excluído com sucesso");
		} catch (RuntimeException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemDeErro("Falha ao tentar excluir");

		}
	}

	public void editar(ActionEvent evento) throws Exception {

		setor = (Setor) evento.getComponent().getAttributes().get("setorSelecionado");
		JSFUtil.adicionarMessagemDeSucesso("Setor editado com sucesso");

	}

}
