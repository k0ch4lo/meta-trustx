inherit trustmeimage

do_trustme_bootpart () {

	if [ -z "${DEPLOY_DIR_IMAGE}" ];then
		bbfatal "Cannot get bitbake variable \"DEPLOY_DIR_IMAGE\""
		exit 1
	fi

	if [ -z "${TRUSTME_BOOTPART_DIR}" ];then
		bbfatal "Cannot get bitbake variable \"TRUSTME_BOOTPART_DIR\""
		exit 1
	fi

	bbnote "Copying boot partition files to ${TRUSTME_BOOTPART_DIR}"
	
	machine=$(echo "${MACHINE}" | tr "_" "-")
	bbdebug 1 "Boot machine: $machine"

	rm -fr "${TRUSTME_BOOTPART_DIR}"
	install -d "${TRUSTME_BOOTPART_DIR}/BOOT/EFI"
	cp --dereference "${DEPLOY_DIR_IMAGE}/bzImage-initramfs-${machine}.bin.signed" "${TRUSTME_BOOTPART_DIR}/BOOT/EFI/BOOT64.EFI"
}

do_trustme_bootpart[depends] = "virtual/kernel:do_deploy"
