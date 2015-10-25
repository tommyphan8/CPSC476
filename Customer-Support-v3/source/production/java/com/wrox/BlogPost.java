package com.wrox;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlogPost
{
    private String customerName;

    private String subject;

    private String body;

    private String timeStamp;

    private Map<String, Attachment> attachments = new LinkedHashMap<>();

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp;}

    public String getTimeStamp() {return timeStamp;}

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public Attachment getAttachment(String name)
    {
        return this.attachments.get(name);
    }

    public Collection<Attachment> getAttachments()
    {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment)
    {
        this.attachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments()
    {
        return this.attachments.size();
    }
}
