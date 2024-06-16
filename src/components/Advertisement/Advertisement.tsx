import { useState } from "react";

const time = new Date();

const maintainingTime = Number(
  `${time.getFullYear()}${
    time.getMonth() < 10 ? "0" + time.getMonth() : time.getMonth()
  }${time.getDay() < 10 ? "0" + time.getDay() : time.getDay()}`
);
const advertDismissedString = localStorage.getItem("advertDismissed");
const advertDismissed =
  advertDismissedString && JSON.parse(advertDismissedString);

function Advertisement() {
  const [isVisible, setIsVisible] = useState(true);

  const handleAdvert = () => {
    setIsVisible(false);
    setTimeout(() => {
      localStorage.setItem("advertDismissed", JSON.stringify(maintainingTime));
    }, 300);
  };
  return (
    <>
      {(maintainingTime - advertDismissed > 3 || advertDismissed == null) && (
        <div
          className={`fixed w-full left-0 right-0 z-10 bg-turquoise-color bg-opacity-50 p-5 r-container shadow-md transition-transform duration-500 ${
            isVisible ? "translate-y-0" : "-translate-y-full"
          }`}
        >
          <span className="font-bold">
            공지사항 📢 드디어 우리 사이트가 오픈했어요 !!!
          </span>

          <div className="r-container">
            <span>3일동안 닫기</span>
            <button onClick={handleAdvert}>X</button>
          </div>
        </div>
      )}
    </>
  );
}

export default Advertisement;
