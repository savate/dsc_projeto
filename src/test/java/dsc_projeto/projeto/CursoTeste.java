/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import dsc_projeto.projeto.tipos.TiposCurso;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class CursoTeste extends Teste{
    @Test
    public void persistirCurso() {
        Curso curso = new Curso();
        
        curso.setNomeCurso("Design Gráfico");
        curso.setTipoCurso(TiposCurso.TECNICO);
        
        Aluno aluno = em.find(Aluno.class, 8);
        
        Collection<Aluno> alunos = new HashSet<>();
        alunos.add(aluno);
        curso.setAlunos(alunos);
        
        Professor professor = em.find(Professor.class, 4);
        
        Collection<Professor> professores = new HashSet<>();
        professores.add(professor);
        curso.setProfessores(professores);
        
        Disciplina disciplina = em.find(Disciplina.class, 4);
        
        Collection<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(disciplina);
        curso.setDisciplinas(disciplinas);
        
        em.persist(curso);
        em.flush();
        
        assertNotNull(curso.getId());
    }
    
    @Test
    public void consultarCurso() {
        Curso curso = em.find(Curso.class, 2);
        assertNotNull(curso);
        assertEquals("DESIGN", curso.getNomeCurso());
        assertEquals(TiposCurso.POSGRADUACAO, curso.getTipoCurso());
        assertEquals(1, curso.getAlunos().size());
        assertEquals(1, curso.getProfessores().size());
        assertEquals(1, curso.getDisciplinas().size());
    }
}
