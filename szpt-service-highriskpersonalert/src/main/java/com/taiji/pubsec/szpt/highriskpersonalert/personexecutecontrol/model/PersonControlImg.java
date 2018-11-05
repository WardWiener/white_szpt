package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model;

import java.io.InputStream;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.complex.tools.attachment.AttachmentService;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentCopyImpl;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentMetaImpl;
import com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlService;

@Entity
@Table(name = "t_gwry_rybk_bkzp")
public class PersonControlImg {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
    
    /**
     * 关联的实战平台通用附件的id
     */
    @Column(name="ywfj_id")
    private String szptAttachmentId ;
    
	@OneToOne(targetEntity = DefaultAttachmentMetaImpl.class)
	@JoinColumn(name="fjy_id", unique=true)
	private AttachmentMeta attachmentMeta ;
    
    @ManyToOne
    @JoinColumn(name = "rybk_id")
    private PersonExecuteControl personExecuteControl;
    
    private PersonControlImg(){
    	
    }
    
    /**
     * 新增并保存，将参数传入，new一个实例，保存到数据库，并返回持久化实例
     * @param personExecuteControlId personExecuteControlId的id
     * @param fileName 文件名
     * @param fileStream 文件输入流，调用方需要在finally里关闭
     * @return
     */
    public static PersonControlImg getAlreadyPersistedInstance(String personExecuteControlId, String szptAttachmentId, String fileName, InputStream fileStream){
    	
    	PersonControlImg entity = new PersonControlImg() ;
    	
    	DefaultAttachmentMetaImpl am = new DefaultAttachmentMetaImpl();
    	am.setName(fileName);
    	DefaultAttachmentCopyImpl ac = new DefaultAttachmentCopyImpl();
		ac.setCreateTime(new Date());
		
        //按数据库系统的格式存
        ac.setStorageType(DatabaseAttachment.class.getName());
        am.getAttachmentCopies().add(ac);
        
        AttachmentService attachmentService = SpringContextUtil.getApplicationContext().getBean(AttachmentService.class);
        attachmentService.create(am, fileStream);
        
        entity.setAttachmentMeta(am); 
        
        IPersonExecuteControlService personExecuteControlService = SpringContextUtil.getApplicationContext().getBean(IPersonExecuteControlService.class);
        
        PersonExecuteControl pc = personExecuteControlService.findById(personExecuteControlId) ;
        if(pc==null){
        	throw new IllegalArgumentException("查询不到id为"+personExecuteControlId+"的PersonExecuteControl") ;
        }
        
        entity.setPersonExecuteControl(pc);
        entity.setAttachmentMeta(am);
        entity.setSzptAttachmentId(szptAttachmentId);
        
        PersonControlImgService personControlImgService = SpringContextUtil.getApplicationContext().getBean(PersonControlImgService.class);
        personControlImgService.save(entity);
        
        return entity ;
    }
    
	public String getName(){
		if(this.getAttachmentMeta()!=null){
			return this.getAttachmentMeta().getName() ;
		}
		return null;
	}
	
	public Long getSize(){
		if(this.getAttachmentMeta()!=null && this.getAttachmentMeta().getAttachmentCopies().size()>0){
			return Long.valueOf(this.getAttachmentMeta().getAttachmentCopies().get(0).getProperty("filelenth").toString()) ;
		}else{
			return Long.valueOf(0) ;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AttachmentMeta getAttachmentMeta() {
		return attachmentMeta;
	}

	public void setAttachmentMeta(AttachmentMeta attachmentMeta) {
		this.attachmentMeta = attachmentMeta;
	}

	public PersonExecuteControl getPersonExecuteControl() {
		return personExecuteControl;
	}

	public void setPersonExecuteControl(PersonExecuteControl personExecuteControl) {
		this.personExecuteControl = personExecuteControl;
	}

	public String getSzptAttachmentId() {
		return szptAttachmentId;
	}

	public void setSzptAttachmentId(String szptAttachmentId) {
		this.szptAttachmentId = szptAttachmentId;
	}
    
    
}
