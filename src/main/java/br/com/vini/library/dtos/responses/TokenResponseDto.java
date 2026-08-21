package br.com.vini.library.dtos.responses;

public record TokenResponseDto(String token, long expiresIn) {
}
