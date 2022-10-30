/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.Test;

public class AlunoTeste extends Teste{
    @Test
    public void persistirAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno da Silva");
        aluno.setMatricula("2222x2-1111");
        aluno.setEstado("PE");
        aluno.setCurso("TADS");
        aluno.setIdade(20);
        aluno.setCidade("Recife");
        aluno.setPeriodo("1 Periodo");
        Calendar c = Calendar.getInstance();
        c.set(2002, Calendar.SEPTEMBER, 24, 0, 0, 0);
        aluno.setDataNascimento(c.getTime());
        aluno.addDisciplinas("Iniciação a programação");
        aluno.addDisciplinas("Lógica de programação");
        
        Contato contato = new Contato();
        contato.setEmail("alunodasilva@gmail.com");
        contato.setTelefone("81 9999-8877");
        contato.setCaixaPostal("41159");
        aluno.setContato(contato);
        
        em.persist(aluno);
        em.flush();
        
        assertNotNull(aluno.getId());
    }
    
    @Test
    public void consultarAluno() {
        Aluno aluno = em.find(Aluno.class, 1);
        assertNotNull(aluno);
        assertEquals("Carlitos", aluno.getNome());
        assertEquals("20222y3-9999", aluno.getMatricula());
        Calendar c = Calendar.getInstance();
        c.set(2002, Calendar.MAY, 11, 0, 0, 0);
        assertEquals(c.getTime().toString(), aluno.getDataNascimento().toString());
        
        Contato contato = aluno.getContato();
        assertNotNull(contato);
        assertEquals("juvenaldo1234@gmail.com", contato.getEmail());
        assertEquals("81 9999-9999", contato.getTelefone());
        assertEquals("70981", contato.getCaixaPostal());
        
    }
}
