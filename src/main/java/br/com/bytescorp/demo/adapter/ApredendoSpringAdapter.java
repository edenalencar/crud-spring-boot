/*
 * Copyright (c) 2020 BytesCorp Software
 * Todos os direitos reservados
 *
 * Este produto está protegido por direitos autorais e distribuído sob
 * licenças que restringem a cópia, distribuição e descompilação.
 * @author Eden Alencar
 * @since 1.0
 *
 */

package br.com.bytescorp.demo.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApredendoSpringAdapter implements WebMvcConfigurer {
    @Autowired
    private final PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

    public ApredendoSpringAdapter(PageableHandlerMethodArgumentResolver PageableHandlerMethodArgumentResolver) {
        this.pageableHandlerMethodArgumentResolver = PageableHandlerMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        pageableHandlerMethodArgumentResolver.setFallbackPageable(PageRequest.of(0, 10));
        resolvers.add(pageableHandlerMethodArgumentResolver);
    }
}
