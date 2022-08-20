package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Specialization;
import com.example.repository.SpecializationRepository;
import com.example.service.ISpecialization;
@Service
public class SpecializationServiceImpl implements ISpecialization {
@Autowired
private SpecializationRepository repo;
	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Specialization getoneSpecialization(Long id) {
		Optional<Specialization> optional = repo.findById(id);
		if(optional.isPresent()) {
		return optional.get() ;
	}
		else {
			return null;
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

}
