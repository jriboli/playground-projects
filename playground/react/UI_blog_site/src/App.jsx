import { HomePage, BlogsPage, BlogContentPage } from "./pages";
import { Routes, Route } from "react-router-dom";

export default function App() {

  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/blogs" element={<BlogsPage />}> </Route>
        <Route path="/blogs/category/:category" element={<BlogsPage />}> </Route>
        <Route path="/blog/:id" element={<BlogContentPage />}></Route>
      </Routes>
    </div>
  );
}
