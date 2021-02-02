package com.hepta.funcionarios.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.funcionarios.entity.Setor;

public class SetorDAO {

	public void salvar(Setor setor) throws Exception {

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(setor);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public Setor buscarPorCodigo(Integer id) throws Exception {

		EntityManager em = HibernateUtil.getEntityManager();
		Setor setor = null;
		try {
			setor = em.find(Setor.class, id);
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
		return setor;

	}

	public void delete(Setor setor) throws Exception {

		EntityManager em = HibernateUtil.getEntityManager();

		try {

			em.getTransaction().begin();
			Setor setor1 = em.merge(setor);
			em.remove(setor1);
			em.getTransaction().commit();

		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Setor> listar() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Setor> setores = new ArrayList<>();
		try {

			Query query = em.createQuery("SELECT setor FROM Setor setor");
			setores = query.getResultList();

		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
		return setores;

	}

	public Setor editar(Setor setor) throws Exception {

		EntityManager em = HibernateUtil.getEntityManager();
		Setor setorAtualizado = null;

		try {

			em.getTransaction().begin();
			setorAtualizado = em.merge(setor);
			em.getTransaction().commit();

		} catch (RuntimeException e) {

			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
		return setorAtualizado;

	}
	
	public void merge(Setor setor) throws Exception {

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(setor);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

}
