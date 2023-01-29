package com.laiofferflagcamp.community.service;

import com.laiofferflagcamp.community.exception.AnnouncementNotFoundException;
import com.laiofferflagcamp.community.model.Announcement;
import com.laiofferflagcamp.community.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement createAnnouncement(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Long id, Announcement announcement) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setMessage(announcement.getMessage());
        return announcementRepository.save(existingAnnouncement);
    }

    public void deleteAnnouncement(Long id) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        announcementRepository.delete(existingAnnouncement);
    }
}
