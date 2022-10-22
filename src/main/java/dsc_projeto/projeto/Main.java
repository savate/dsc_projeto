/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        preencherUsuario(usuario);
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            emf = Persistence.createEntityManagerFactory("dsc_projeto");
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(usuario);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    private static void preencherUsuario(Usuario usuario) {
        usuario.setNome("Ricardo da Silva");
        usuario.setIdade(19);
        usuario.setEmail("ricardosilva123@gmail.com");
        usuario.setSenha("senha1234");
    }
}
