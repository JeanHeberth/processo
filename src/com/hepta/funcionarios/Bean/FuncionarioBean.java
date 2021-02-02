/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hepta.funcionarios.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hepta.funcionarios.entity.Funcionario;
import com.hepta.funcionarios.entity.Setor;
import com.hepta.funcionarios.persistence.FuncionarioDAO;
import com.hepta.funcionarios.persistence.SetorDAO;
import com.hepta.funcionarios.util.JSFUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;

/**
 *
 * @author jean.vieira
 */
@ViewScoped
@ManagedBean(name = "MBFuncionario")
public class FuncionarioBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    private List<Setor> setores;

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @PostConstruct
    public void listar() {
        try {

            FuncionarioDAO fDao = new FuncionarioDAO();
            funcionarios = fDao.getAll();

        } catch (Exception e) {
            JSFUtil.adicionarMensagemDeErro("Erro ao listar");
            e.printStackTrace();
        }
    }

    public void novo() {
        try {
            funcionario = new Funcionario();
            SetorDAO sDao = new SetorDAO();
            setores = sDao.listar();
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() throws Exception {
        try {

            FuncionarioDAO fDao = new FuncionarioDAO();
            fDao.merge(funcionario);

            funcionario = new Funcionario();
            SetorDAO sDao = new SetorDAO();
            setores = sDao.listar();
            funcionarios = fDao.getAll();
            JSFUtil.adicionarMessagemDeSucesso("Funcionário com sucesso!");

        } catch (RuntimeException e) {
            JSFUtil.adicionarMensagemDeErro("Falha ao salvar usuário!");
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

            FuncionarioDAO fDao = new FuncionarioDAO();
            fDao.delete(funcionario);

            funcionarios = fDao.getAll();

            JSFUtil.adicionarMessagemDeSucesso("Excluído com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemDeErro("Falha ao tentar excluir");
        }

    }

    public void editar(ActionEvent evento) throws Exception {
        try {
            funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
            SetorDAO sDao = new SetorDAO();
            setores = sDao.listar();
            JSFUtil.adicionarMessagemDeSucesso("Setor editado com sucesso");
        } catch (Exception e) {
            JSFUtil.adicionarMensagemDeErro("Falha ao editar");
            e.printStackTrace();
        }

    }
}
