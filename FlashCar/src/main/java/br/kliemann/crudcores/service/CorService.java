package br.kliemann.crudcores.service;

import br.kliemann.crudcores.exception.DescricaoInvalidaException;
import br.kliemann.crudcores.model.Cor;
import br.kliemann.crudcores.repository.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorService {
    
    private void validaCor(Cor cor) throws DescricaoInvalidaException {
        
        if (cor.getDesc() == null) {
            throw new DescricaoInvalidaException();
        }
        
        if (cor.getDesc().trim().length() == 0) {
            throw new DescricaoInvalidaException();
        }
        
        if (cor.getDesc().trim().length() > 60) {
            throw new DescricaoInvalidaException();
        }
        
    }
    
     public void insert(Cor cor) throws DescricaoInvalidaException, SQLException {
        
        validaCor(cor);
        CorRepository corRepository = new CorRepository();
        corRepository.insert(cor);
        
    }
    
    public ArrayList<Cor> findAll() throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        return corRepository.findAll();
        
    }
    
    public void delete(Cor cor) throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        corRepository.delete(cor);
        
    }
    
    public void update(Cor cor) throws SQLException, DescricaoInvalidaException {
        
        validaCor(cor);
        CorRepository corRepository = new CorRepository();
        corRepository.update(cor);
        
    }
    
}
