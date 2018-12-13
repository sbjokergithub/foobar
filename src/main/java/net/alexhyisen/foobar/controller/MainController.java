package net.alexhyisen.foobar.controller;

import net.alexhyisen.foobar.service.MainService;
import net.alexhyisen.foobar.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/api/{uid}/paper")
    public Collection<Publication> papers(@PathVariable long uid,
                                          @RequestParam(value = "skip", defaultValue = "0") long skip,
                                          @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findPublications(uid, skip, limit);
    }

    @GetMapping("/api/{uid}/friend")
    public Collection<Person> friends(@PathVariable long uid,
                                      @RequestParam(value = "skip", defaultValue = "0") long skip,
                                      @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findFriends(uid, skip, limit);
    }

    @GetMapping("/api/{uid}/moment")
    public Collection<Publication> moments(@PathVariable long uid,
                                           @RequestParam(value = "skip", defaultValue = "0") long skip,
                                           @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findMoments(uid, skip, limit);
    }

    @GetMapping("/api/{uid}/stranger")
    public Collection<Person> strangers(@PathVariable long uid,
                                        @RequestParam(value = "skip", defaultValue = "0") long skip,
                                        @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findStrangers(uid, skip, limit);
    }

    @GetMapping("/api/{srcUid}/agent/{dstUid}")
    public Collection<Person> agents(@PathVariable long srcUid, @PathVariable long dstUid,
                                     @RequestParam(value = "skip", defaultValue = "0") long skip,
                                     @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findAgents(srcUid, dstUid, skip, limit);
    }

    @PostMapping("/api/{uid}/paper")
    public Publication createPaper(@PathVariable long uid, @RequestBody Paper paper) {
        return mainService.addPaper(uid, paper.getTitle(), paper.getContent());
    }

    @DeleteMapping("/api/{uid}/paper/{pid}")
    public void removePaper(@PathVariable long uid, @PathVariable long pid) {
        mainService.delPaper(uid, pid);
    }

    @PostMapping("/api/register")
    public Link createUser(@RequestBody RegisterInfo info) {
        return mainService.addUser(info);
    }

    @PostMapping("/api/{srcUid}/invite/{dstUid}")
    public Invitation addInvitation(@PathVariable long srcUid, @PathVariable long dstUid, @RequestBody String message) {
        return mainService.createInvitation(srcUid, dstUid, message);
    }

    @DeleteMapping("/api/{srcUid}/invite/{dstUid}")
    public void delInvitation(@PathVariable long srcUid, @PathVariable long dstUid) {
        mainService.deleteInvitation(srcUid, dstUid);
    }

    @GetMapping("/api/{uid}/invite/import")
    public Collection<Invitation> importInvitations(@PathVariable long uid,
                                                    @RequestParam(value = "skip", defaultValue = "0") long skip,
                                                    @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findImportInvitations(uid, skip, limit);
    }

    @GetMapping("/api/{uid}/invite/export")
    public Collection<Invitation> exportInvitations(@PathVariable long uid,
                                                    @RequestParam(value = "skip", defaultValue = "0") long skip,
                                                    @RequestParam(value = "limit", defaultValue = "10") long limit) {
        return mainService.findExportInvitations(uid, skip, limit);
    }

    @PostMapping("/api/{invitationDstUid}/accept/{invitationSrcUid}")
    public void acceptInvitation(@PathVariable long invitationSrcUid, @PathVariable long invitationDstUid) {
        mainService.acceptInvitation(invitationSrcUid, invitationDstUid);
    }

    @DeleteMapping("/api/{srcUid}/friend/{dstUid}")
    public void breakFriendship(@PathVariable long srcUid, @PathVariable long dstUid) {
        mainService.breakFriendship(srcUid, dstUid);
    }
}