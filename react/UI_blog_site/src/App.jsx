import { Homepage, BlogContentPage } from "./pages";
import { Routes, Route } from "react-router-dom";
import useFetch from "./hooks/useFetch";

export default function App() {
  let { loading, error, data } = useFetch("http://127.0.0.1:1337/blogs");
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error!</p>;

  return (
    <div>
      <Routes>
        <Route path="/" element={<Homepage blogs={data ? data : ""} />}></Route>
        <Route path="/blog/:id" element={<BlogContentPage />}></Route>
      </Routes>
    </div>
  );
}
