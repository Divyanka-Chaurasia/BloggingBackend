package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.bean.PostTags;

public interface IPostTagsRepo extends JpaRepository<PostTags, Integer> {

}
