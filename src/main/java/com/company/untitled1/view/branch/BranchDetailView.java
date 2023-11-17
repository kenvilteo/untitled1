package com.company.untitled1.view.branch;

import com.company.untitled1.entity.*;
import com.company.untitled1.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@Route(value = "branches/:id", layout = MainView.class)
@ViewController("Branch.detail")
@ViewDescriptor("branch-detail-view.xml")
@EditedEntityContainer("branchDc")
public class BranchDetailView extends StandardDetailView<Branch> {
    private static final Logger log = LoggerFactory.getLogger(BranchDetailView.class);

    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private JmixComboBox<BranchType> branchTypeField;
    @ViewComponent
    private JmixComboBox<ListBankFake> bankCodeField;
    @ViewComponent
    private CollectionContainer<Branch> branchesDc;
    @ViewComponent
    private EntityComboBox<Branch> parentBranchField;
    @ViewComponent
    private CollectionLoader<Branch> branchesDl;
    @ViewComponent
    private JmixComboBox<DirectMenber> isDirectField;
    @ViewComponent
    private EntityComboBox<Branch> tradeByField;
    @ViewComponent
    private JmixComboBox<ActiveStatus> statusField;
    @ViewComponent
    private TypedTextField<String> branchNameField;

    public BranchDetailView() {
    }

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomString(int STRING_LENGTH) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static int generateRandomNumber(int NUMNBER_MAX) {
        Random random = new Random();
        return random.nextInt(NUMNBER_MAX);
    }

    public static String generateRandomBranch(String type,int NUMNBER_MAX) {
        Random random = new Random();
        int v_int = random.nextInt(NUMNBER_MAX);
//        String result = null;
        return null;
    }



    @Subscribe
    public void onInitEntity(final InitEntityEvent<Branch> event) {
        branchCodeField.setReadOnly(false);
        tradeByField.setVisible(false);

        //mặc định trạng thái Is direct, Active
        Branch branch= event.getEntity();
        branch.setBankCode(ListBankFake.SOTAI);
        branch.setBranchType(BranchType.HO);
        branch.setIsDirect(DirectMenber.STOP);
        branch.setStatus(ActiveStatus.ACTIVE);

        //gen tự động mã + tên branch
        int codeGenAuto = generateRandomNumber(1000);
        branch.setBranchCode("CODE_"+codeGenAuto);
        branch.setBranchName("NAME_"+codeGenAuto);

    }

    @Subscribe("isDirectField")
    public void onIsDirectFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixComboBox<DirectMenber>, DirectMenber> event) {

        try{
//            if(event.getValue() != null){
//                log.info("log event "+event.getValue().toString());
//                tradeByField.setVisible((DirectMenber.INDIRECT.equals(event.getValue())));
//            }
//            //neeus là màn thêm mới (trường branchCode readonly = false) thì set mặc định = STOP
//            if(!branchCodeField.isReadOnly()){
//                isDirectField.setValue(DirectMenber.STOP);
//            }

            tradeByField.setVisible((DirectMenber.INDIRECT.equals(event.getValue())));
        }catch(Exception e){
            log.info(e.getMessage());
        }

//        if (isDirectField.getValue() != null && event.getValue() != null){
//            tradeByField.setVisible(("INDIRECT".equals(
//                    (isDirectField.getValue()==null?event.getValue():isDirectField.getValue())
//                            .toString())));
//            log.info(isDirectField.getValue().toString());
//            log.info(event.getValue().toString());
//           log.info("1111111111111");
//       }else{
//            log.info("2222222222222");
//        }


//        log.info(isDirectField.getValue().toString());
//
//        if (event.getOldValue() != null){
//            //nếu là Thành viên gián tiếp thì hiển thị Trade by bắt nhập
//            tradeByField.setVisible(("INDIRECT".equals(isDirectField.getValue().toString())));
//            log.info("1111111111111111111");
//        }else {
//            isDirectField.setValue(DirectMenber.STOP);
//            log.info("22222222222222222");
//        }

//        if ((!event.isFromClient()) && (event.getOldValue() == null) ) {
//            isDirectField.setValue(DirectMenber.STOP);
//            log.info("trennnnnnnnnnnnnnnnnnnn");
//        } else {
//            tradeByField.setVisible(("INDIRECT".equals(isDirectField.getValue().toString())));
//            log.info("duoiiiiiiiiiiiiiiiiiiii");
//        }
    }


    @Subscribe("branchTypeField")
    public void onBranchTypeFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixComboBox<BranchType>, BranchType> event) {
        String vType = String.valueOf(event.getValue());
        String vResult = vType.equals("HO") ? null : (vType.equals("L1") ? "HO" : "L1");
        branchesDl.setParameter("branchTypeInput", vResult);
        branchesDl.setParameter("bankCodeInput", bankCodeField.getValue());
        branchesDl.load();
        //reset giá trị trường parentBranch
        parentBranchField.setValue(null);
        //set thử bankCode = null
//        bankCodeField.setValue("");
    }

    @ViewComponent
    private TypedTextField<String> branchCodeField;

    @Subscribe("bankCodeField")
    public void onBankCodeFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixComboBox<ListBankFake>, ListBankFake> event) {
//        String vResult = String.valueOf(event.getValue());
//        branchesDl.setParameter("branchTypeInput", branchTypeField.getValue());
//        branchesDl.setParameter("bankCodeInput", vResult);
//        branchesDl.load();
        //reset giá trị trường parentBranch
        parentBranchField.setValue(null);
    }

    //tự định nghĩa giá trị
    @Subscribe("parentBranchField")
    public void onParentBranchFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<Branch>> event) {
        Branch parentBranch = dataManager.create(Branch.class);
        parentBranch.setBranchCode(event.getDetail());
        parentBranch.setBranchName(event.getDetail());
        branchesDc.getMutableItems().add(parentBranch);
        parentBranchField.setValue(parentBranch);
    }

    @Subscribe("saveAndCloseBtn")
    public void onSaveAndCloseBtnClick(final ClickEvent<JmixButton> event) {
//        log.info("violation.getMessage()");
//        log.error("violation.getMessage()");
    }





}