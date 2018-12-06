package net.alexhyisen.foobar;

import net.alexhyisen.foobar.module.Person;
import net.alexhyisen.foobar.module.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;

@Service
public class MainService {
    private final MainRepository mainRepository;

    @Autowired
    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Transactional(readOnly = true)
    public Collection<Publication> findPublications(long uid, long skip, long limit) {
        return mainRepository.findPublications(uid, skip, limit);
    }

    @Transactional(readOnly = true)
    public Collection<Person> findFriends(long uid, long skip, long limit) {
        return mainRepository.findFriends(uid, skip, limit);
    }

    @Transactional(readOnly = true)
    public Collection<Publication> findMoments(long uid, long skip, long limit) {
        return mainRepository.findMoments(uid, skip, limit);
    }

    @Transactional(readOnly = true)
    public Collection<Person> findStrangers(long uid, long skip, long limit) {
        return mainRepository.findStrangers(uid, skip, limit);
    }

    @Transactional(readOnly = true)
    public Collection<Person> findAgents(long srcUid, long dstUid, long skip, long limit) {
        return mainRepository.findAgents(srcUid, dstUid, skip, limit);
    }

    @Transactional()
    public Publication addPaper(long uid, String title, String content) {
        var timestamp = Instant.now().toEpochMilli();
        //TODO: a better procedure to generate pid
        var pid = timestamp % 1000;
        return mainRepository.addPaper(uid, pid, timestamp, title, content);
    }

    @Transactional()
    public void delPaper(long uid, long pid) {
        mainRepository.delPaper(uid, pid);
    }
}
