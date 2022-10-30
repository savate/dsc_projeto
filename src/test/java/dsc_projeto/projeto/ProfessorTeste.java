/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class ProfessorTeste extends Teste{
    @Test
    public void persistirProfessor() {
        Professor professor = new Professor();
        professor.setNome("Professor dos Santos");
        professor.setIdade(45);
        professor.setTitulo("Mestre");
        professor.setEspecializacao("Criptografia");
        professor.setEstado("PE");
        professor.setCidade("Jaboatão");
        Calendar c = Calendar.getInstance();
        c.set(1991, Calendar.OCTOBER, 12, 0, 0, 0);
        professor.setDataNascimento(c.getTime());
        
        Contato contato = new Contato();
        contato.setEmail("professordossantos@gmail.com");
        contato.setTelefone("81 7777-8887");
        contato.setCaixaPostal("10141");
        professor.setContato(contato);
        
        Turma turma = new Turma();
        turma.setCurso("CC");
        turma.setNomeTurma("Criptografia em sites");
        turma.setPeriodo("5 Periodo");
        turma.setCapacidade(40);
        turma.setProfessor(professor);
        
        List<Turma> turmas = new ArrayList<>();
        turmas.add(turma);
        
        professor.setTurmas(turmas);
        
        em.persist(professor);
        em.flush();
        
        assertNotNull(professor.getId());
        assertNotNull(turma.getId());
    }
    
    @Test
    public void consultarProfessor() {
        Professor professor = em.find(Professor.class, 2);
        assertNotNull(professor);
        assertEquals("Alvaro da Silva", professor.getNome());
        assertEquals("PE", professor.getEstado());
        assertEquals("Camaragibe", professor.getCidade());
        Calendar c = Calendar.getInstance();
        c.set(1972, Calendar.SEPTEMBER, 21, 0, 0, 0);
        assertEquals(c.getTime().toString(), professor.getDataNascimento().toString());
        
        Contato contato = professor.getContato();
        assertNotNull(contato);
        assertEquals("dralvaro@gmail.com", contato.getEmail());
        assertEquals("81 9999-8888", contato.getTelefone());
        assertEquals("70001", contato.getCaixaPostal());
       
        
    }
}
