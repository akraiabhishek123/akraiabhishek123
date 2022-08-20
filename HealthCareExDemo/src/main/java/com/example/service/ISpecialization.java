package com.example.service;

import java.util.List;

import com.example.entity.Specialization;

public interface ISpecialization {
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getoneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);

}
