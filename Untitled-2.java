Usuário fixo:
Email: admin@qima.com

Senha: admin123 (criptografada com BCrypt)

java
Copiar
Editar
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin@qima.com")
        .password(passwordEncoder().encode("admin123"))
        .roles("ADMIN");