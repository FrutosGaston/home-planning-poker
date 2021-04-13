package org.unq.pokerplanning.config;

public enum ErrorCode {

    INTERNAL_ERROR(100, "Error interno del servidor"),
    REST_CLIENT_TIMEOUT(101, "Timeout de cliente rest"),
    INSERT_JDBC(103, "Error al actualizar movimiento en la base"),
    POKEMON_TIMEOUT(103, "El llamado a Pokemon devolvio error"),
    ABILITY_NOT_FOUND(104, "No se encontro la Habilidad del pokemon"),
    ABILITY_TIMEOUT(105, "El llamado a Ability devolvio error"),
    TYPE_NOT_FOUND(106, "No se encontro el Tipo del pokemon"),
    TYPE_TIMEOUT(107, "El llamado a Type devolvio error"),
    WEB_CLIENT_GENERIC(108, "Error del Web Client"),
    BAD_SQL_FORMAT(109, "Error al intentar leer la consulta sql");

    private final int value;
    private final String reasonPhrase;

    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
