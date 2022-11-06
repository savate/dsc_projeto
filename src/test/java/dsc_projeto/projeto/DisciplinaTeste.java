/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class DisciplinaTeste extends Teste{
    @Test
    public void persistirDisciplina() {
        Disciplina disciplina = new Disciplina();
        
        disciplina.setNomeDisciplina("Banco de Dados 2");
        disciplina.setCapacidade(30);
        disciplina.setPeriodo("4 Periodo");
        
        Professor professor = em.find(Professor.class, 1);
        disciplina.setProfessor(professor);
        
        Collection<Aluno> alunos = new HashSet<>();
        Aluno aluno1 = em.find(Aluno.class, 5);
        Aluno aluno2 = em.find(Aluno.class, 6);
        alunos.add(aluno1);
        alunos.add(aluno2);
        disciplina.setAlunos(alunos);
        
        Curso curso = em.find(Curso.class, 1);
        disciplina.setCurso(curso);
        
        em.persist(curso);
        em.flush();
        
        assertNotNull(curso.getId());
    }
    
    @Test
    public void consultarDisciplina() {
        Disciplina disciplina = em.find(Disciplina.class, 2);
        assertNotNull(disciplina);
        assertEquals("Web 1", disciplina.getNomeDisciplina());
        assertEquals((Integer) 40, disciplina.getCapacidade());
        assertEquals("2 Periodo", disciplina.getPeriodo());
        assertEquals((Integer) 7, disciplina.getAlunos().iterator().next().getId());
        assertEquals((Integer) 8, disciplina.getRepresentante().getId());
        assertEquals((Integer) 2, disciplina.getProfessor().getId());
        assertEquals((Integer) 1, disciplina.getCurso().getId());
    }
}
