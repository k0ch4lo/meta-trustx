FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://trustx.cfg"

do_copy_signing_tool (){
    if [ "${TRUSTME_MODSIGNING}" == 1]; then
        mkdir -p "${STAGING_KERNEL_BUILDDIR}"
    
        if [ -f "${B}/scripts/sign-file" ]; then
            cp "${B}/scripts/sign-file" "${STAGING_KERNEL_BUILDDIR}/"
        else
            bberror "Failed copying sign-file from cp ${B}/scripts/"
        fi
    fi
}

addtask do_copy_signing_tool after do_compile before do_build 
