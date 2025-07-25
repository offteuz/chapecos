package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.mapper.MenuMapper;
import br.com.fiap.chapecos.model.Menu;
import br.com.fiap.chapecos.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    public MenuResponseDTO create(MenuRequestDTO dto) {
        Menu menu = menuMapper.toModel(dto);

        return new MenuResponseDTO(menuRepository.save(menu));
    }
}
