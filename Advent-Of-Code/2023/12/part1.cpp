#include <fstream>
#include <sstream>
#include <iostream>
#include <string>
#include <vector>


class SpringRow {

private:
    std::string row;
    std::vector<int> ranges;


    std::vector<int> range_for(std::string row) {
        std::vector<int> range;
        int current_range = 0;
        for (std::size_t i = 0; i < row.size(); i++) {
            char c = row[i];
            if (c == '#')
                current_range++;
            else if (current_range != 0) {
                std::cout << current_range << std::endl;
                range.push_back(current_range);
                current_range = 0;
            }
        }
        if (current_range != 0)
            range.push_back(current_range);
        return range;
    }


public:
    SpringRow(std::string def) {
        std::size_t split_index = def.find(" ");
        std::string row_def = def.substr(0, split_index);
        this->row = row_def;

        std::string ranges_def = def.substr(split_index + 1);
        std::stringstream ranges_stream(ranges_def);
        while (ranges_stream.good()) {
            std::string range;
            std::getline(ranges_stream, range, ',');
            this->ranges.push_back(std::stoi(range));
        }
    }


    std::string& get_row() {
        return this->row;
    }


    std::vector<int>& get_ranges() {
        return this->ranges;
    }


    int num_possibilities() {
        return range_for("###..#.....#####")[0];
    }

};


int main() {
    std::ifstream infile("input.txt");
    std::string line;
    std::vector<SpringRow> rows;
    while (std::getline(infile, line))
        rows.emplace_back(line);
    //    int ans = 0;
    //    for (int i = 0; i < rows.size(); i++) {
    //        SpringRow& row = rows[i];
    //        ans += row.num_possibilities();
    //    }
    std::cout << "ANSWER: " << rows[0].num_possibilities() << std::endl;
    return 0;
}
