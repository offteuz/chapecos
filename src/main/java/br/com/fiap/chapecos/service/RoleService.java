package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.RoleRequestDTO;
import br.com.fiap.chapecos.dto.response.RoleResponseDTO;
import br.com.fiap.chapecos.exception.RoleNotFoundException;
import br.com.fiap.chapecos.mapper.RoleMapper;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleResponseDTO create(RoleRequestDTO dto) {
        return new RoleResponseDTO(roleRepository.save(roleMapper.role(dto)));
    }

    public List<RoleResponseDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleResponseDTO::new)
                .toList();
    }

    public RoleResponseDTO findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);

        return new RoleResponseDTO(role);
    }

    public RoleResponseDTO update(Long id, RoleRequestDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);

        roleMapper.role(dto);

        return new RoleResponseDTO(roleRepository.save(role));
    }

    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);

        roleRepository.delete(role);
    }
}
