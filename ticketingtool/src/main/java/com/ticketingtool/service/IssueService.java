package com.ticketingtool.service;

import com.ticketingtool.modal.Issue;
import com.ticketingtool.modal.User;
import com.ticketingtool.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {


    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue udpateStatus(Long issueId, String status) throws Exception;



}
