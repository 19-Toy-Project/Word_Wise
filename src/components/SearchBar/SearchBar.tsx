import { useEffect, useRef } from "react";
import SearchIcon from "../../styles/images/search-icon.png";
function SearchBar() {
  const searchRef = useRef<HTMLInputElement | null>(null);

  useEffect(() => {
    if (searchRef.current) {
      searchRef.current.focus();
    }
  }, []);
  const handleSearch = () => {};
  return (
    <div className="max-w-[400px] relative m-auto text-center">
      <input
        ref={searchRef}
        placeholder="찾으실 단어를 입력해주세요"
        required
      />
      <img
        className="max-w-[25px] absolute top-2 right-4 hover:cursor-pointer"
        src={SearchIcon}
        alt="search icon"
        onClick={handleSearch}
      />
    </div>
  );
}

export default SearchBar;
