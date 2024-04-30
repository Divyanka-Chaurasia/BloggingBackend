package com.dollop.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.bean.Tags;

public interface IRepoTags extends JpaRepository<Tags, Integer> {

	Optional<Tags> findByTagsId(Integer tagsId);
}
