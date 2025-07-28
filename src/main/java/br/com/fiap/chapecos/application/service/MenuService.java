package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.exception.MenuNotFoundException;
import br.com.fiap.chapecos.mapper.MenuMapper;
import br.com.fiap.chapecos.model.Menu;
import br.com.fiap.chapecos.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<MenuResponseDTO> findAll() {
        return menuRepository.findAll()
                .stream()
                .map(MenuResponseDTO::new)
                .toList();
    }

    public MenuResponseDTO findById(Long idMenu) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(MenuNotFoundException::new);

        return new MenuResponseDTO(menu);
    }

    public void delete(Long idMenu) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(MenuNotFoundException::new);

        menuRepository.delete(menu);
    }
}
