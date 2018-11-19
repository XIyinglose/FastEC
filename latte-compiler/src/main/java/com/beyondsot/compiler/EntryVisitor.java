package com.beyondsot.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;


final class EntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private final Filer FILER;
    private String mPackageName = null;

    EntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    /**
     *  生成類
     * @param t
     * @param p
     * @return
     */
    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    /**
     * 生成Java代碼
     * @param typeMirror
     */
    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity") //類名稱
                        .addModifiers(Modifier.PUBLIC) //public
                        .addModifiers(Modifier.FINAL) //final
                        .superclass(TypeName.get(typeMirror))  //繼承那個類
                        .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity) //包名
                .addFileComment("微信入口文件")  //注釋
                .build();
        try {
            javaFile.writeTo(FILER); //寫入文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
