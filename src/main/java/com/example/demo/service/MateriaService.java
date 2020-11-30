package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Materia;
import com.example.demo.repository.MateriaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Autowired
    NotaService notaService;

    @Autowired
    private MateriaMapper mapper;

    public MateriaDTO findById(Long id){
        Optional<Materia> materiaOptional = materiaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(materiaOptional.isEmpty()){
            throw new NotFoundException("Matéria não encontrada");
        }

        return mapper.toMateriaDTO(materiaOptional.get());
    }

    public List<MateriaDTO> findAll(){
        return materiaRepository.findAllByActive(Boolean.TRUE)
                                .parallelStream()
                                .map(mapper::toMateriaDTO)
                                .collect(Collectors.toList());
    }

    public Page<MateriaDTO> findAll(Pageable pageable){
        Page<Materia> materias = materiaRepository.findAllByActive(Boolean.TRUE, pageable);
        Page<MateriaDTO> materiasP = new PageImpl<MateriaDTO>(materias.getContent().parallelStream()
                .map(mapper::toMateriaDTO)
                .collect(Collectors.toList()), materias.getPageable(), materias.getTotalElements());
        return materiasP;
    }

    public Long save(MateriaDTO materiaDTO){
        Materia materia = mapper.toMateria(materiaDTO);

        materia = materiaRepository.save(materia);

        materiaDTO.setId(materia.getId());

        return materiaDTO.getId();
    }

    public Long save(MateriaDTO materiaDTO, Long id) throws NotFoundException{
        materiaDTO.setId(id);

        if(!materiaRepository.existsByIdAndActive(id, Boolean.TRUE)){
            throw new NotFoundException("Matéria não encontrada");
        }

        Materia novaMateria = mapper.toMateria(materiaDTO);

        novaMateria.setId(id);

        materiaRepository.save(novaMateria);

        return novaMateria.getId();
    }

    public boolean deleteById(Long id) throws NotFoundException{
        Optional<Materia> materia = materiaRepository.findByActiveAndId(Boolean.TRUE, id);

        if(materia.isEmpty()){
            throw new NotFoundException("Matéria não encotnrada");
        }

        materia.get().setActive(Boolean.FALSE);

        try {
            notaService.inativarNota(mapper.toMateriaDTO(materia.get()));
        } catch(NotFoundException e){

        } finally {
            materiaRepository.save(materia.get());

            return true;
        }
    }
}
