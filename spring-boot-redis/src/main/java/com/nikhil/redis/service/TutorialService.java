package com.nikhil.redis.service;

import java.net.SocketException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nikhil.redis.model.Tutorial;
import com.nikhil.redis.repository.TutorialRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TutorialService {

    private static final String CACHE_PREFIX = "tutorials:";

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Fetch tutorial by ID with dynamic cache switching.
     */
    public Optional<Tutorial> findById(long id) throws SocketException {
        String cacheKey = CACHE_PREFIX + id;
        
        try {
            // Try fetching from Redis
            if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
                log.info("✅ Fetching tutorial from Redis: ID {}", id);
                return Optional.ofNullable((Tutorial) redisTemplate.opsForValue().get(cacheKey));
            }
        } catch (RedisConnectionFailureException e) {
            log.warn("⚠️ Redis unavailable ({}), switching to database", e.getMessage());
        } catch (Exception e) {
            log.warn("⚠️ Unexpected Redis error: {}", e.getMessage());
        }

        // If Redis is down, fetch from database
        log.info("Fetching tutorial from Database: ID {}", id);
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);

        // Store in Redis if available
        tutorial.ifPresent(t -> {
            try {
                redisTemplate.opsForValue().set(cacheKey, t, 10, TimeUnit.MINUTES);
                log.info("✅ Stored tutorial in Redis: ID {}", id);
            } catch (RedisConnectionFailureException e) {
                log.warn("⚠️ Redis unavailable ({}), skipping cache storage", e.getMessage());
            } catch (Exception e) {
                log.warn("⚠️ Unexpected Redis error: {}", e.getMessage());
            }
        });

        return tutorial;
    }

    /**
     * Save a tutorial and update Redis cache.
     */
    public Tutorial save(Tutorial tutorial) {
        Tutorial savedTutorial = tutorialRepository.save(tutorial);
        String cacheKey = CACHE_PREFIX + savedTutorial.getId();
        
        try {
            redisTemplate.opsForValue().set(cacheKey, savedTutorial, 10, TimeUnit.MINUTES);
            log.info("✅ Stored tutorial in Redis: ID {}", savedTutorial.getId());
        } catch (Exception e) {
            log.warn("⚠️ Could not store in Redis.");
        }

        return savedTutorial;
    }

    /**
     * Delete tutorial from database and remove from cache.
     */
    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
        String cacheKey = CACHE_PREFIX + id;
        
        try {
            redisTemplate.delete(cacheKey);
            log.info("✅ Deleted tutorial from Redis: ID {}", id);
        } catch (Exception e) {
            log.warn("⚠️ Could not delete from Redis.");
        }
    }

    /**
     * Fetch all tutorials with caching.
     */
    public List<Tutorial> findAll() {
        String cacheKey = CACHE_PREFIX + "all";
        
        try {
            if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
                log.info("✅ Fetching all tutorials from Redis");
                return (List<Tutorial>) redisTemplate.opsForValue().get(cacheKey);
            }
        } catch (Exception e) {
            log.warn("⚠️ Redis unavailable, fetching from database.");
        }

        // If Redis is down, fetch from DB
        List<Tutorial> tutorials = tutorialRepository.findAll();

        try {
            redisTemplate.opsForValue().set(cacheKey, tutorials, 10, TimeUnit.MINUTES);
            log.info("✅ Stored all tutorials in Redis");
        } catch (Exception e) {
            log.warn("⚠️ Could not store in Redis.");
        }

        return tutorials;
    }
}
