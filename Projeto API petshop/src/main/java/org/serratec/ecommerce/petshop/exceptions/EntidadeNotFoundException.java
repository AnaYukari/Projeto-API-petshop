package org.serratec.ecommerce.petshop.exceptions;

public class EntidadeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntidadeNotFoundException(String nomeEntidade,
                                     String msg, Integer id) {
        super(msg + nomeEntidade + " com o id = " + id);
    }

    public EntidadeNotFoundException(String msg) {
        super(msg);
    }

}
