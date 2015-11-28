/**
 * @(#)WebListpageNewFile.java
 * 
 * Copyright csair.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1.0
 * @JDK: jdk jdk1.6.0_10
 * @Module: deployPlugin-curd
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2013-1-29     Administrator    Created
 **********************************************
 */

/*
 * Copyright (c) OpenKoala 2011 All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.openkoala.koala.deploy.curd.module.pojo;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import forkoala.org.apache.velocity.VelocityContext;
import org.openkoala.koala.action.velocity.VelocityUtil;
import org.openkoala.koala.deploy.curd.generator.VelocityContextUtils;
import org.openkoala.koala.deploy.curd.module.core.EntityModel;
import org.openkoala.koala.deploy.curd.module.ui.UIWidget;
import org.openkoala.koala.deploy.curd.module.ui.model.AddModel;
import org.openkoala.koala.deploy.curd.module.ui.model.EntityViewUI;
import org.openkoala.koala.deploy.curd.module.ui.model.UpdateModel;
import org.openkoala.koala.pojo.MavenProject;

/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2013-1-29
 */
/**
 * 类    名：WebListpageNewFile.java
 *   
 * 功能描述：具体功能做描述。	
 *  
 * 创建日期：2013-1-29下午7:57:12     
 * 
 * 版本信息：v1.0
 * 
 * 版权信息：Copyright (c) 2011 OpenKoala All Rights Reserved
 * 
 * 作    者：vakinge(chiang.www@gmail.com)
 * 
 * 修改记录： 
 * 修 改 者    修改日期     文件版本   修改说明	
 */

public class WebFormpageNewFile extends NewFile {

    private static final String TEMPLATE_PATH = "templates/formPageTemplate.vm";
    
    /**
     * 关联的领域实体模型
     */
    private EntityModel entityModel;
    
    /**
     * 新增模型
     */
    private AddModel addModel;
    
    /**
     * 更新模型
     */
    private UpdateModel updateModel;
    
    /**
     * @param name
     * @param projects
     * @param type
     */
    public WebFormpageNewFile(String name, List<MavenProject> projects,
            NewFileType type,EntityViewUI entityUI) {
        super(name, projects, type);
        addModel = entityUI.getAddModel();
        updateModel = entityUI.getUpdateModel();
        entityModel = entityUI.getEntityModel();
    }
    
    @Override
	public String getPath() {
        String targetPath = MessageFormat.format("{0}/src/main/webapp/pages/{1}/{2}-{3}", 
                projectPath,entityModel.getLastPackageName(),entityModel.getName(),getName());
        return targetPath;
	}
    
    /*
     *@see org.openkoala.koala.deploy.curd.module.pojo.NewFile#process()
     */
    @Override
    public void process() {
        VelocityContext context = VelocityContextUtils.getVelocityContext();
        context.put("pageParams", this);
        try {
            VelocityUtil.vmToFile(context, TEMPLATE_PATH, getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntityModel getEntityModel() {
        return entityModel;
    }

    public void setEntityModel(EntityModel entityModel) {
        this.entityModel = entityModel;
    }

    public AddModel getAddModel() {
        return addModel;
    }


    public void setAddModel(AddModel addModel) {
        this.addModel = addModel;
    }


    public UpdateModel getUpdateModel() {
        return updateModel;
    }


    public void setUpdateModel(UpdateModel updateModel) {
        this.updateModel = updateModel;
    }
    
    public List<UIWidget> getFormWidgets() {
        if(getIsAddForm()){
            return addModel.getViews();
        }else{
            return updateModel.getViews();
        }
    }
    
    public boolean getIsAddForm(){
        return NewFileType.WEB_ADD.equals(getType());
    }

}
