package org.serratec.ecommerce.petshop.records;

import java.util.Set;

public record UserRecord(String email, String senha, Set<String> role) {
}
