import { useDispatch, useSelector } from "react-redux";
import { modalClose } from "../../redux/slices/modal.slice";
import { toastOpen } from "../../redux/slices/toast.slice";
import BackDrop from "../BackDrop";

function Modal() {
  const dispatch = useDispatch();
  const modal = useSelector((state) => state.modal.modalOptions);

  const handleConfirm = () => {
    dispatch(toastOpen("메시지"));
    dispatch(modalClose());
  };
  return (
    <BackDrop>
      <div
        className="container text-center c-container w-1/4 shadow-md "
        onClick={(e) => e.stopPropagation()}
      >
        <h1 className="text-white-color">시스템 알림</h1>
        <hr />
        <h1 className="text-white-color m-10 text-xl">{modal}</h1>
        <div className="r-container">
          <button className="btn-confirm" onClick={handleConfirm}>
            확인
          </button>
          <button
            className="btn-confirm"
            onClick={() => dispatch(modalClose())}
          >
            취소
          </button>
        </div>
      </div>
    </BackDrop>
  );
}

export default Modal;
